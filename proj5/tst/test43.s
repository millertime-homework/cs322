	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
	mov 2,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 5)]
	mov 5,%l0
	st %l0,[%l1]
! [MOVE (MEM (BINOP + (TEMP 1) (NAME wSZ))) (CONST 7)]
	mov 7,%l0
	mov %l1,%l2
	mov 4,%l3
	add %l2,%l3,%l2
	st %l0,[%l2]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [CALLST (NAME print) ( (BINOP + (FIELD (VAR 1) 0) (FIELD (VAR 1) 1)))]
	ld [%fp-4],%l0
	ld [%l0],%l2
	ld [%fp-4],%l3
	ld [%l3+1],%l4
	add %l2,%l4,%l2
	ld [%l2],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 34
