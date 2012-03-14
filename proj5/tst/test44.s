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
! [MOVE (MEM (BINOP + (TEMP 1) (NAME wSZ))) (BINOP + (FIELD (TEMP 1) 0) (CONST 2))]
	mov %l1,%l0
	ld [%l0],%l2
	mov 2,%l3
	add %l2,%l3,%l2
	mov %l2,%l2
	mov %l1,%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l2,[%l3]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l2
	st %l2,[%fp-4]
! [CALLST (NAME print) ( (BINOP + (FIELD (VAR 1) 0) (FIELD (VAR 1) 1)))]
	ld [%fp-4],%l2
	ld [%l2],%l3
	ld [%fp-4],%l4
	ld [%l4+1],%l5
	add %l3,%l5,%l3
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  6
!Total insts: 38
