	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 1)]
	mov 1,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
	mov 2,%l0
	mov 4,%l2
	smul %l0,%l2,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l2
	mov %o0,%l2
! [MOVE (MEM (TEMP 2)) (CONST 1)]
	mov 1,%l0
	st %l0,[%l2]
! [MOVE (MEM (BINOP + (TEMP 2) (NAME wSZ))) (CONST 2)]
	mov 2,%l0
	mov %l2,%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [MOVE (VAR 2) (TEMP 2)]
	mov %l2,%l0
	st %l0,[%fp-8]
! [CALLST (NAME print) ( (FIELD (VAR 1) 0))]
	ld [%fp-4],%l0
	ld [%l0],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (FIELD (VAR 2) 0))]
	ld [%fp-8],%l3
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (FIELD (VAR 2) 1))]
	ld [%fp-8],%l4
	ld [%l4+1],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  7
!Total insts: 51
